import { ButtonHeader } from "@/components/Header"
import TableCompetitions from "@/components/TableCompetitions"
import { BACK_BASE_URL } from "@/helper/environment"
import { getTranslation } from "@/locales/TranslationHelper"
import axios from "axios"
import { useEffect, useState } from "react"

export default function Competitions() {
    const t = getTranslation()
    const [competitions, setCompetitions] = useState([])

    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/competition`)
            .then(response => { console.log(response.data); setCompetitions(response.data) })
            .catch(_error => {})
    }, [])

    return(
        <div className="page">
            <ButtonHeader title={t.competition_header_title} />
            <TableCompetitions 
                competitions={competitions}
            />
        </div>
    )
}