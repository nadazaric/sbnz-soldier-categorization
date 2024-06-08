/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useState } from "react";
import styleForm from '../styles/Form.module.css'
import { getTranslation } from "@/locales/TranslationHelper";
import { Button } from "@mui/material";

export default function AddCompetitor({
    competitionId,
    isOpen,
    onSave
}) {
    const t = getTranslation()
    const [form, setForm] = useState({
        competitionId: '',
        fullName: '',
        jmbg: '',
        deadFamilyMember: 'NONE',
        injuryType: 'NONE'
    })

    useEffect(() => {
        setForm({...form, competitionId: competitionId})
    }, [competitionId])

    useEffect(() => {
        setForm({
            competitionId: competitionId,
            fullName: '',
            jmbg: '',
            deadFamilyMember: 'NONE',
            injuryType: 'NONE'
        })
    }, [isOpen])

    function isButtonDisabled() {
        return form.competitionId == '' || form.fullName == '' || form.jmbg == '' || 
            form.deadFamilyMember == '' || form.injuryType == ''
    }

    return(
        <form
            className={styleForm.form}
            onSubmit={(e) => {if (onSave) onSave(e, form)}}
        >
            <div className={`${styleForm.inputWrapper} width_full`}>
                <input 
                    className={styleForm.input}
                    value={form.fullName}
                    placeholder={t.competitor_name}
                    onChange={(e) => {setForm({...form, fullName: e.target.value})}}  
                />
            </div>
            <div className='spacer_hor_S' />
            <div className={`${styleForm.inputWrapper} width_full`}>
                <input 
                    className={styleForm.input}
                    value={form.jmbg}
                    placeholder={t.competitor_jmbg}
                    onChange={(e) => {setForm({...form, jmbg: e.target.value})}}  
                />
            </div>
            <div className='spacer_hor_S' />
            <div className={styleForm.borderedDiv}>
                <span className={styleForm.cornerText}>{t.competitor_dead_family_member}</span>
                <div className={styleForm.radioButtonWrapper}>
                    <label className={styleForm.squareButtonLabel}>
                        <input
                            className={styleForm.squareButton}
                            type="radio"
                            value="NONE"
                            name={`type-family-member`}
                            checked={form.deadFamilyMember === 'NONE'}
                            onChange={(e) => setForm({...form, deadFamilyMember: e.target.value})}
                        />
                        {t.competitor_none}
                    </label>
                    <label className={styleForm.squareButtonLabel}>
                        <input
                            className={styleForm.squareButton}
                            type="radio"
                            value="SPOUSE"
                            name={`type-family-member`}
                            checked={form.deadFamilyMember === 'SPOUSE'}
                            onChange={(e) => setForm({...form, deadFamilyMember: e.target.value})}
                        />
                        {t.competitor_family_member_spouse}
                    </label>
                    <label className={styleForm.squareButtonLabel}>
                        <input
                            className={styleForm.squareButton}
                            type="radio"
                            name={`type-family-member`}
                            value="CHILD"
                            checked={form.deadFamilyMember === 'CHILD'}
                            onChange={(e) => setForm({...form, deadFamilyMember: e.target.value})}
                        />
                        {t.competitor_family_member_child}
                    </label>
                </div>
            </div>
            <div className='spacer_hor_S' />
            <div className={styleForm.borderedDiv}>
                <span className={styleForm.cornerText}>{t.competitor_injury}</span>
                <div className={styleForm.radioButtonWrapper}>
                    <label className={styleForm.squareButtonLabel}>
                        <input
                            className={styleForm.squareButton}
                            type="radio"
                            value="NONE"
                            name={`type-injury`}
                            checked={form.injuryType === 'NONE'}
                            onChange={(e) => setForm({...form, injuryType: e.target.value})}
                        />
                        {t.competitor_none}
                    </label>
                    <label className={styleForm.squareButtonLabel}>
                        <input
                            className={styleForm.squareButton}
                            type="radio"
                            value="LOW"
                            name={`type-injury`}
                            checked={form.injuryType === 'LOW'}
                            onChange={(e) => setForm({...form, injuryType: e.target.value})}
                        />
                        {t.competitor_injuries_type_low}
                    </label>
                    <label className={styleForm.squareButtonLabel}>
                        <input
                            className={styleForm.squareButton}
                            type="radio"
                            name={`type-injury`}
                            value="MEDIUM"
                            checked={form.injuryType === 'MEDIUM'}
                            onChange={(e) => setForm({...form, injuryType: e.target.value})}
                        />
                        {t.competitor_injuries_type_medium}
                    </label>
                    <label className={styleForm.squareButtonLabel}>
                        <input
                            className={styleForm.squareButton}
                            type="radio"
                            name={`type-injury`}
                            value="HIGH"
                            checked={form.injuryType === 'HIGH'}
                            onChange={(e) => setForm({...form, injuryType: e.target.value})}
                        />
                        {t.competitor_injuries_type_high}
                    </label>
                </div>
            </div>
            <div className='spacer_hor_S' />
            <Button 
                className='raisedButton'
                type='submit'
                disabled={isButtonDisabled()}
            >
                {t.button_save}
            </Button>
        </form>
    )
}