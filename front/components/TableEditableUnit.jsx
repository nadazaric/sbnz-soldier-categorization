import { getTranslation } from "@/locales/TranslationHelper"
import React, { useEffect, useState } from 'react'
import style from '../styles/Table.module.css'
import styleChip from '../styles/Chip.module.css'

export default function TableEditableUnit({
    formMode=false,
    units,
    onChange
}){
    const [entities, setEntities] = useState([])
    const t = getTranslation()

    useEffect(() =>{
        setEntities(units)
    }, [units])

    const handleChipClick = (index) => {
        const updatedUnitOptions = [...entities]
        updatedUnitOptions[index].selected = !updatedUnitOptions[index].selected
        setEntities(updatedUnitOptions)
        if (onChange) onChange()
    }
    
    return(
        <div>
            <div>
                {entities != null && entities.length != 0 &&
                    <div>
                        <div className={styleChip.wrapper}>
                            {!formMode && units.map((unit, index) => (
                                <React.Fragment key={index}>
                                    {unit.selected && (
                                        <div className={`${styleChip.option} ${styleChip.optionSelected}`}>
                                            {unit.text}
                                        </div>
                                    )}
                                </React.Fragment>
                            ))}
                            {formMode && units.map((unit, index) => (
                                <React.Fragment key={index}>
                                    <div 
                                        className={`${styleChip.option} ${unit.selected ? styleChip.optionSelected : style.optionNotSelected}`}
                                        onClick={() => handleChipClick(index)}
                                    >
                                        {unit.text}
                                    </div>
                                </React.Fragment>
                            ))}
                        </div>
                    </div>
                }
            </div>
        </div>
    )
}