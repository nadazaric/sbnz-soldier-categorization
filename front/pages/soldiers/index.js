/* eslint-disable react-hooks/exhaustive-deps */
import { DialogWithHeader } from "@/components/Dialog";
import { ButtonHeader } from "@/components/Header";
import { AddDetailsSoldier } from "@/components/AddSoldier";
import TableSoldiers from "@/components/TableSoldiers";
import { BACK_BASE_URL } from "@/helper/environment";
import { getLanguage, getTranslation } from "@/locales/TranslationHelper";
import axios from "axios";
import { useEffect, useState } from "react";
import { DropdownMenuOption } from "@/components/DropdownMenu";

export default function Soldiers() {
    const t = getTranslation()
    const language = getLanguage()
    const [soldiers, setSoldiers] = useState(null)
    const [openDialog, setOpenDialog] = useState(false)
    const [selectedId, setSelectedId] = useState('')
    const [haveError, setHaveError] = useState(false)
    const [errorMessage, setErrorMessage] = useState('')

    // units
    const [selectedUnitId, setSelectedUnitId] = useState(null)
    const [unitOption, setUnitOptns] = useState([{value: '', text: ''}])
    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/unit/units-except-soldier`)
            .then(response => { 
                var options = []
                options.push({value: null, text: t.unit_default_option})
                for (var option of response.data) options.push({value: option.id, text: option.name})
                setUnitOptns(options) 
            })
            .catch(_error => {})
    }, [language])

    function getSoldiersForUnit() {
        axios.get(`${BACK_BASE_URL}/unit/soldiers-for-unit/${selectedUnitId}`)
            .then(response => { setSoldiers(response.data) })
            .catch(_error => {})
    }

    // soldiers
    function getAllSoldiers() {
        axios.get(`${BACK_BASE_URL}/soldier`)
            .then(response => { setSoldiers(response.data) })
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
                getAllSoldiers()
            }
        })
        .catch(error => {
            setHaveError(true)
            if (error.response.status == 400) setErrorMessage(t.error_jmbg_save_soldier)
            else setErrorMessage(t.error_save_soldier)
        })
    }

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
            />
            <DialogWithHeader
                isOpen={openDialog}
                width={600}
                onCloseModal={() => setOpenDialog(false)}
                title={selectedId ? t.soldiers_details : t.soldiers_add_title}
                haveError={haveError}
                errorDescription={errorMessage}
            >
                <AddDetailsSoldier 
                    selectedId={selectedId}
                    onSave={saveSoldier}
                    isOpen={openDialog}
                    onChange={() => setHaveError(false)}
                />
            </DialogWithHeader>
        </div>
    )
}