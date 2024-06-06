import { DialogWithHeader } from "@/components/Dialog";
import { ButtonHeader } from "@/components/Header";
import { AddDetailsSoldier } from "@/components/AddSoldier";
import TableSoldiers from "@/components/TableSoldiers";
import { BACK_BASE_URL } from "@/helper/environment";
import { getTranslation } from "@/locales/TranslationHelper";
import axios from "axios";
import { useEffect, useState } from "react";

export default function Soldiers() {
    const t = getTranslation()
    const [soldiers, setSoldiers] = useState(null)
    const [openDialog, setOpenDialog] = useState(false)
    const [selectedId, setSelectedId] = useState('')

    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/soldier`)
            .then(response => { setSoldiers(response.data) })
            .catch(_error => {})
    }, [])

    function onSoldierClick(soldier) {
        setSelectedId(soldier.id)
        setOpenDialog(true)
    }

    function onAddClick() {
        setOpenDialog(true)
        setSelectedId(null)
    }

    const saveSoldier = async (formData) => {
        try {
            const response = await axios.post(`${BACK_BASE_URL}/soldier`, formData, {
                headers: { 'Content-Type': 'application/json' }
            })
            if (response.status === 201) {
                setSoldiers(prevSoldiers => [...prevSoldiers, response.data])
                setOpenDialog(false)
            }
            else { }
        } catch (error) { }
    }

    return(
        <div className='page'>
            <ButtonHeader 
                title={t.soldiers_header_title}
                onAddClick={onAddClick} 
            />
            <div className="spacer_hor_M" />
            <TableSoldiers 
                soldiers={soldiers} 
                onSoldierClick={onSoldierClick}
                onAddClick={onAddClick}
            />
            <DialogWithHeader
                isOpen={openDialog}
                width={600}
                onCloseModal={() => setOpenDialog(false)}
                title={selectedId ? t.soldiers_details : t.soldiers_add_title}
            >
                <AddDetailsSoldier 
                    selectedId={selectedId}
                    onSave={saveSoldier}
                    isOpen={openDialog}
                />
            </DialogWithHeader>
        </div>
    )
}