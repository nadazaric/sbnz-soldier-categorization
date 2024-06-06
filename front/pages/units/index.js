import { BACK_BASE_URL } from "@/helper/environment"
import axios from "axios"
import { useEffect, useState } from "react"

export default function Units() {
    const [soldiers, setSoldiers] = useState([])
    const [selectedId, setSelectedId] = useState(3)

    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/unit/soldiers-for-unit/${selectedId}`)
            .then(response => {console.log(response.data); setSoldiers(response.data) })
            .catch(_error => {})
    }, [])

    return(
        <div>
            {"Units"}
        </div>
    )
}