import AddCompetition from "@/components/AddCompetition"
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
            .then(response => { sortCompetitions(response.data) })
            .catch(_error => {})
    }, [])

    const sortCompetitions = (data) => {
        const sortedCompetitions = data.sort((a, b) => {
            if (a.isDone === b.isDone) return 0
            else if (a.isDone) return 1
            else return -1
        })
        setCompetitions(sortedCompetitions)
    }

    // show details
    const [openDetailsDialog, setOpenDetailsDialog] = useState(false)

    function onCompetitionClick(soldier) {
        setSelected(soldier)
        setOpenDetailsDialog(true)
    }

    // add competition
    const [openAddCompeitionDialog, setOpenAddCompetitionDialog] = useState(false)

    function saveCompetition(e, competition) {
        e.preventDefault()
        axios.post(`${BACK_BASE_URL}/competition`, competition, {
            headers: { 'Content-Type': 'application/json' }
        })
        .then(response => {
            if (response.status === 201) {
                setOpenAddCompetitionDialog(false)
                var c = [...competitions, response.data]
                sortCompetitions(c)
            }
        })
        .catch(error => {})
    }

    return(
        <div className="page">
            <ButtonHeader 
                title={t.competition_header_title} 
                onAddClick={() => setOpenAddCompetitionDialog(true)}
            />
            <TableCompetitions 
                competitions={competitions}
                onCompeitionClick={onCompetitionClick}
            />

            <DialogWithHeader
                isOpen={openDetailsDialog}
                width={1200}
                onCloseModal={() => setOpenDetailsDialog(false)}
                title={t.competition_details_title}
            >
                <DeatilsCompetition competition={selected} isOpen={openDetailsDialog} />
            </DialogWithHeader>

            <DialogWithHeader
                isOpen={openAddCompeitionDialog}
                width={400}
                onCloseModal={() => setOpenAddCompetitionDialog(false)}
                title={t.competition_add_title}
            >
                <AddCompetition 
                    isOpen={openAddCompeitionDialog}
                    onSave={saveCompetition} 
                />
            </DialogWithHeader>
        </div>
    )
}