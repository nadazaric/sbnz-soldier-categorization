/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable react/jsx-no-undef */
import React, { useEffect, useState } from 'react'
import style from '../styles/Table.module.css'
import styleForm from '../styles/Form.module.css'
import DeleteIcon from '@mui/icons-material/DeleteOutline'
import { getTranslation } from '@/locales/TranslationHelper'

export default function TableEditableInjury({ injuries, onChange, onDelete }) {
  const [entities, setEntities] = useState([])
  const t = getTranslation()

  useEffect(() =>{
    setEntities(injuries)
  }, [injuries])

  const handleChange = (index, e) => {
    const { name, value, type, checked } = e.target
    const [fieldName, fieldIndex] = name.split('-')
    const newValue = type === 'checkbox' ? checked : (type === 'date' ? new Date(value) : value)
    const newEntities = [...entities]
    newEntities[index][fieldName] = newValue
    setEntities(newEntities)
    if (onChange) onChange(newEntities)
  }

  function onDeleteClick(e, index) {
    e.preventDefault()
    if (onDelete) onDelete(index)
  }
  
  return (
    <div>
        <div>
          {entities != null && entities.length != 0 &&
            <div>
              <div className={style.rowInjury}>
                  <div className={`${style.item} ${style.headerItem}`}>{t.war_obligation_start_date}</div>
                  <div className={`${style.item} ${style.headerItem}`}/>
              </div>
              { entities.map((injury, index) => (
                  <React.Fragment key={index}>
                      <div className={`${style.rowInjury} ${style.itemRow} ${index % 2 === 0 ? style.grayRow : ''}`}>
                          <div className={`${style.item}`}>
                              <div className={styleForm.radioButtonWrapper}>
                                  <label className={styleForm.squareButtonLabel}>
                                      <input
                                          className={styleForm.squareButton}
                                          type="radio"
                                          value="LOW"
                                          name={`type-${index}`}
                                          checked={injury.type === 'LOW'}
                                          onChange={(e) => handleChange(index, e)}
                                      />
                                      {t.war_obligation_type_war_zone}
                                  </label>
                                  <label className={styleForm.squareButtonLabel}>
                                      <input
                                          className={styleForm.squareButton}
                                          type="radio"
                                          name={`type-${index}`}
                                          value="MEDIUM"
                                          checked={injury.type === 'MEDIUM'}
                                          onChange={(e) => handleChange(index, e)}
                                      />
                                      {t.war_obligation_type_work_duty}
                                  </label>
                                  <label className={styleForm.squareButtonLabel}>
                                      <input
                                          className={styleForm.squareButton}
                                          type="radio"
                                          name={`type-${index}`}
                                          value="HIGH"
                                          checked={injury.type === 'HIGH'}
                                          onChange={(e) => handleChange(index, e)}
                                      />
                                      {t.war_obligation_type_work_duty}
                                  </label>
                              </div>
                          </div>
                          <div className={`${style.item}`}> 
                            <DeleteIcon 
                              className='icon'
                              onClick={(e) => onDeleteClick(e, index)}
                            /> 
                          </div>
                        </div>
                  </React.Fragment>
              ))}
            </div>
          }
        </div>
    </div>
  )
}