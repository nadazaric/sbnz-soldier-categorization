import DeatilsCompetition from "@/components/DetailsCompetition"
import { DialogWithHeader } from "@/components/Dialog"
import { ButtonHeader } from "@/components/Header"
import TableCompetitions from "@/components/TableCompetitions"
import { BACK_BASE_URL } from "@/helper/environment"
import { getTranslation } from "@/locales/TranslationHelper"
import axios from "axios"
import { useEffect, useState } from "react"

export default function Competitions() {
    const t = getTranslation()
    const [competitions, setCompetitions] = useState([])
    const [selected, setSelected] = useState('')

    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/competition`)
            .then(response => { console.log(response.data); setCompetitions(response.data) })
            .catch(_error => {})
    }, [])

    // show details
    const [openDetailsDialog, setOpenDetailsDialog] = useState(false)

    function onCompetitionClick(soldier) {
        setSelected(soldier)
        setOpenDetailsDialog(true)
    }

    return(
        <div className="page">
            <ButtonHeader title={t.competition_header_title} />
            <TableCompetitions 
                competitions={competitions}
                onCompeitionClick={onCompetitionClick}
            />
            <DialogWithHeader
                isOpen={openDetailsDialog}
                width={1000}
                onCloseModal={() => setOpenDetailsDialog(false)}
                title={t.competition_details_title}
            >
                <DeatilsCompetition competition={selected} isOpen={openDetailsDialog} />
            </DialogWithHeader>
        </div>
    )
}