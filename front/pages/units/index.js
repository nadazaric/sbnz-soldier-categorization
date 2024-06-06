/* eslint-disable react-hooks/exhaustive-deps */
import { AddDetailsSoldier } from "@/components/AddSoldier"
import { DialogWithHeader } from "@/components/Dialog"
import TableSoldiers from "@/components/TableSoldiers"
import { BACK_BASE_URL } from "@/helper/environment"
import { getTranslation } from "@/locales/TranslationHelper"
import axios from "axios"
import { useEffect, useState } from "react"

export default function Units() {
    const t = getTranslation()
    const [soldiers, setSoldiers] = useState([])
    const [selectedUnitId, setSelectedId] = useState(2)
    const [selectedSoldierId, setSelectedSoldierId] = useState(null);
    const [openDialog, setOpenDialog] = useState(false)

    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/unit/soldiers-for-unit/${selectedUnitId}`)
            .then(response => { setSoldiers(response.data) })
            .catch(_error => {})
    }, [])

    function onSoldierClick(soldier) {
        setSelectedSoldierId(soldier.id)
        setOpenDialog(true)
    }

    return(
        <div className="page">
            <TableSoldiers 
                soldiers={soldiers}
                onSoldierClick={onSoldierClick}
            />
            <DialogWithHeader
                isOpen={openDialog}
                width={600}
                onCloseModal={() => setOpenDialog(false)}
                title={t.soldiers_add_title}
            >
                <AddDetailsSoldier 
                    selectedId={selectedSoldierId}
                    isOpen={openDialog}
                />
            </DialogWithHeader>
        </div>
    )
}