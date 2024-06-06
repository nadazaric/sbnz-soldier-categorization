/* eslint-disable react-hooks/exhaustive-deps */
import { DialogWithHeader } from "@/components/Dialog";
import { ButtonHeader } from "@/components/Header";
import { AddDetailsSoldier } from "@/components/AddSoldier";
import TableSoldiers from "@/components/TableSoldiers";
import { BACK_BASE_URL } from "@/helper/environment";
import { getTranslation } from "@/locales/TranslationHelper";
import axios from "axios";
import { useEffect, useState } from "react";
import { DropdownMenuOption } from "@/components/DropdownMenu";

export default function Soldiers() {
    const t = getTranslation()
    const [soldiers, setSoldiers] = useState(null)
    const [openDialog, setOpenDialog] = useState(false)
    const [selectedId, setSelectedId] = useState('')

    // units
    const [selectedUnitId, setSelectedUnitId] = useState(null)
    const [unitOption, setUnitOptns] = useState([{value: '', text: ''}])
    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/unit/units-except-soldier`)
            .then(response => { 
                var options = []
                options.push({value: null, text: "DEAFULT"})
                for (var option of response.data) options.push({value: option.id, text: option.name})
                setUnitOptns(options) 
            })
            .catch(_error => {})
    }, [])

    function getSoldiersForUnit() {
        axios.get(`${BACK_BASE_URL}/unit/soldiers-for-unit/${selectedUnitId}`)
            .then(response => {console.log('UNIT ' + selectedUnitId); setSoldiers(response.data) })
            .catch(_error => {})
    }

    // soldiers
    function getAllSoldiers() {
        axios.get(`${BACK_BASE_URL}/soldier`)
            .then(response => {console.log('ALL'); setSoldiers(response.data) })
            .catch(_error => {})
    }

    function onSoldierClick(soldier) {
        setSelectedId(soldier.id)
        setOpenDialog(true)
    }

    function onAddClick() {
        setOpenDialog(true)
        setSelectedId(null)
    }

    // globals
    useEffect(() => {
        getSoldiers()
    }, [selectedUnitId])

    function getSoldiers() {
        if (selectedUnitId == null) getAllSoldiers()
        else getSoldiersForUnit()
    }

    const saveSoldier = (formData) => {
        axios.post(`${BACK_BASE_URL}/soldier`, formData, {
            headers: { 'Content-Type': 'application/json' }
        })
        .then(response => {
            if (response.status === 201) {
                setOpenDialog(false)
                setSelectedUnitId(null)
            }
        })
        .catch(error => {});
    };

    return(
        <div className='page'>
            <ButtonHeader 
                title={t.soldiers_header_title}
                onAddClick={onAddClick} 
            >
                <DropdownMenuOption
                    options={unitOption}
                    selectedDefault={selectedUnitId}
                    onSelect={(e) => setSelectedUnitId(e.value)}
                />
            </ButtonHeader>
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