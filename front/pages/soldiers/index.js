import { BACK_BASE_URL } from "@/helper/environment";
import axios from "axios";
import { useEffect } from "react";

export default function Soldiers() {

    // useEffect(() => {
    //     axios.get(`${BACK_BASE_URL}/soldier`, {
    //         // headers: { 'skip': true },
    //         mode: 'cors' // Dodajte ovde mode: 'cors'
    //     })
    //     .then(response => { console.log(response.data) })
    //     .catch(_error => {});
    // }, []);


    useEffect(() => {
        fetch(`${BACK_BASE_URL}/soldier`, {
            method: 'GET',
            headers: {
            //     'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': 'http://localhost:3000'
            },
        })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));
    }, []);
    
    


    return(
        <div>
            Soldiers Page
        </div>
    )
}