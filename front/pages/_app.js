import Layout from "@/components/Layout"
import { BACK_BASE_URL } from "@/helper/environment";
import { getUserAccessToken, getUserRefreshToken } from "@/helper/helper";
import "@/styles/globals.css"
import axios from "axios";

axios.interceptors.request.use(
    config => {
        const token = getUserAccessToken()
        if (token && !config.headers['skip']) {
            config.headers['Authorization'] = `Bearer  ${token.replace(/"/g, '')}`
        }
        return config
    },
    error => {
        Promise.reject(error)
    }
)

axios.interceptors.response.use(
    response => {
      return response;
    },
    async function (error) {
        const originalRequest = error.config;
  
        if (error.response && error.response.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true
            const tokenEndpoint = `${BACK_BASE_URL}/api/user/refreshToken`
            const accessToken = getUserAccessToken()
            const refreshToken = getUserRefreshToken()
  
        return axios.post(tokenEndpoint, {
            'accessToken': accessToken,
            'refreshToken': refreshToken,
        }, {
            headers: {
                'Content-Type': 'application/json',
                'skip': true
            },
        }).then(response => {
            if (response.status === 200) {
                localStorage.setItem('accessToken', response.data['accessToken'])
                localStorage.setItem('refreshToken', response.data['refreshToken'])
                return axios(originalRequest)
            } else {
                logOut();
            }
        }).catch(_err => {
          logOut()
        })
      }
      return Promise.reject(error)
    }
  )

export default function App({ Component, pageProps }) {
    return (
        <Layout>
            <Component {...pageProps} />
        </Layout>
    )
}
