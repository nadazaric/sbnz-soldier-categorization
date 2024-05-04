import { useState } from 'react'
import styleForm from '../styles/Form.module.css'

export function SoldierDetails({ formMode=false }) {

    const [test, setTest] = useState('')

    if (formMode) {
        return (
            <div>
                 <div className={styleForm.inputWrapper}>
                    <input 
                        className={styleForm.input}
                        value={test}
                        placeholder='Ime i prezime'
                        onChange={(e) => {setTest(e.value)}} />
                </div>
            </div>
        )
    } else {
        return (
            <div>
                Not form
            </div>
        )
    }
}