import { ButtonHeader } from "@/components/Header";
import TableSoldiers from "@/components/TableSoldiers";
import { BACK_BASE_URL } from "@/helper/environment";
import { getTranslation } from "@/locales/TranslationHelper";
import axios from "axios";
import { useEffect, useState } from "react";

export default function Soldiers() {
    const t = getTranslation()
    const [soldiers, setSoldiers] = useState(null)

    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/soldier`)
        .then(response => { setSoldiers(response.data) })
        .catch(_error => {})
    }, [])

    return(
        <div className='page'>
            <ButtonHeader title={t.soldiers_header_title} />
            <div className="spacer_hor_M" />
            <TableSoldiers soldiers={soldiers} />
        </div>
    )
}