import { DialogWithHeader } from "@/components/Dialog";
import { ButtonHeader } from "@/components/Header";
import { SoldierDetails } from "@/components/SoldierDetails";
import TableSoldiers from "@/components/TableSoldiers";
import { BACK_BASE_URL } from "@/helper/environment";
import { getTranslation } from "@/locales/TranslationHelper";
import axios from "axios";
import { useEffect, useState } from "react";

export default function Soldiers() {
    const t = getTranslation()
    const [soldiers, setSoldiers] = useState(null)
    const [openDialog, setOpenDialog] = useState(false)

    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/soldier`)
            .then(response => { setSoldiers(response.data) })
            .catch(_error => {})
    }, [])

    function onSoldierClick(soldier) {
        console.log(soldier)
    }

    function onAddClick() {
        setOpenDialog(true)
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
                onCloseModal={() => setOpenDialog(false)}
                title={'Neki title'}
            >
                <SoldierDetails 
                    formMode
                    onSave={saveSoldier}
                />
            </DialogWithHeader>
        </div>
    )
}