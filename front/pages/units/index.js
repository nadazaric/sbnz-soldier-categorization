/* eslint-disable react-hooks/exhaustive-deps */
import { AddDetailsSoldier } from "@/components/AddSoldier"
import { DialogWithHeader } from "@/components/Dialog"
import { DropdownMenuOption } from "@/components/DropdownMenu"
import { Header } from "@/components/Header"
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
    const [unitOption, setUnitOptns] = useState([{value: '', text: ''}])

    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/unit/soldiers-for-unit/${selectedUnitId}`)
            .then(response => { setSoldiers(response.data) })
            .catch(_error => {})
    }, [selectedUnitId])

    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/unit/units-except-soldier`)
            .then(response => { 
                var options = []
                for (var option of response.data) options.push({value: option.id, text: option.name})
                setUnitOptns(options) 
                setSelectedId(response.data[0].id)
            })
            .catch(_error => {})
    }, [])

    function onSoldierClick(soldier) {
        setSelectedSoldierId(soldier.id)
        setOpenDialog(true)
    }

    return(
        <div className="page">
            <Header title={'Title'}>
                <DropdownMenuOption
                    options={unitOption}
                    onSelect={(e) => setSelectedId(e.value)}
                />
            </Header>
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