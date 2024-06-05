import { ButtonHeader } from "@/components/Header";
import TableWorkers from "@/components/TableWorkers";
import { BACK_BASE_URL } from "@/helper/environment";
import { getTranslation } from "@/locales/TranslationHelper";
import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";

export default function Workers() {
    const t = getTranslation()
    const [workers, setWorkers] = useState([])

    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/user/workers`)
            .then(response => { setWorkers(response.data) })
            .catch(_error => {})
    }, [])

    function onAddClick() {
        
    }

    return(
        <div className="page">
            <ButtonHeader 
                title={t.workers_header_title}
                onAddClick={onAddClick} 
            />
            <TableWorkers
                workers={workers}
                onAddClick={onAddClick}
            />
        </div>
    )
}