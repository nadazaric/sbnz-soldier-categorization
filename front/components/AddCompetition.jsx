import { useEffect, useState } from "react"
import styleForm from '../styles/Form.module.css'
import { getTranslation } from "@/locales/TranslationHelper"
import { Button } from "@mui/material"

export default function AddCompetition({
    isOpen,
    onSave
}) {
    const t = getTranslation()
    const [form, setForm] = useState({
        name: '',
        positionNumber: ''
    })

    useEffect(() => {
        setForm({
            name: '',
            positionNumber: ''
        })
    }, [isOpen])

    function isButtonDisabled() {
        return form.name == '' || form.positionNumber == 0
    }

    return(
        <form
            className={styleForm.form}
            onSubmit={(e) => {if (onSave) onSave(e, form)}}
        >
            <div className={`${styleForm.inputWrapper} width_full`}>
                <input 
                    className={styleForm.input}
                    value={form.name}
                    placeholder={t.competition_name}
                    onChange={(e) => {setForm({...form, name: e.target.value})}}  
                />
            </div>
            <div className='spacer_hor_S' />
            <div className={`${styleForm.inputWrapper} width_full`}>
                <input 
                    className={styleForm.input}
                    value={form.positionNumber}
                    placeholder={t.competition_position_name}
                    type="number"
                    min={0}
                    onChange={(e) => {setForm({...form, positionNumber: e.target.value})}}  
                />
            </div>
            <div className='spacer_hor_S' />
            <Button 
                disableRipple
                className='raisedButton'
                type='submit'
                disabled={isButtonDisabled()}
            >
                {t.button_save}
            </Button>
        </form>
    )
}