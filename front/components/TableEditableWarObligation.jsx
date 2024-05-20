/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable react/jsx-no-undef */
import React, { useEffect, useState } from 'react'
import style from '../styles/Table.module.css'
import styleForm from '../styles/Form.module.css'
import DeleteIcon from '@mui/icons-material/DeleteOutline'
import { getTranslation } from '@/locales/TranslationHelper'

export default function TableEditableWarObligation({ obligations, onChange, onDelete }) {
  const [entities, setEntities] = useState([])
  const [isError, setIsError] = useState(false)
  const [errorMessage, setErrorMessage] = useState('')
  const t = getTranslation()

  useEffect(() =>{
    setEntities(obligations)
    const isValid = isValidDateRange()
    if (onChange) onChange(obligations, isValid)
  }, [obligations])

  const handleChange = (index, e) => {
    const { name, value, type, checked } = e.target
    const [fieldName, fieldIndex] = name.split('-')
    const newValue = type === 'checkbox' ? checked : (type === 'date' ? new Date(value) : value)
    const newEntities = [...entities]
    newEntities[index][fieldName] = newValue
    setEntities(newEntities)
    const isValid = isValidDateRange()
    if (onChange) onChange(newEntities, isValid)
  };

  function isValidDateRange(){
    const minDate = new Date(1992, 3, 6)
    const maxDate = new Date(1995, 11, 15)
  
    for (let i = 0; i < obligations.length; i++) {
      const current = obligations[i]
      if (current.startDate < minDate || current.startDate > maxDate) {
        setIsError(true)
        setErrorMessage(t.error_date_not_in_range)
        return false
      }
      if (current.endDate < minDate || current.endDate > maxDate) {
        setIsError(true)
        setErrorMessage(t.error_date_not_in_range)
        return false
      }
      if (current.startDate >= current.endDate) {
        setIsError(true)
        setErrorMessage(t.error_start_date_after_end_date)
        return false
      }
      for (let j = i + 1; j < obligations.length; j++) {
        const next = obligations[j];
        if (current.startDate < next.endDate && current.endDate > next.startDate) {
          setIsError(true)
          setErrorMessage(t.error_date_range_inside_other_range)
          return false
        }
      }
    }
    setIsError(false)
    return true
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
              <div className={style.rowWarObligation}>
                  <div className={`${style.item} ${style.headerItem}`}>{t.war_obligation_start_date}</div>
                  <div className={`${style.item} ${style.headerItem}`}>{t.war_obligation_end_date}</div>
                  <div className={`${style.item} ${style.headerItem}`}>{t.war_obligation_type}</div>
                  <div className={`${style.item} ${style.headerItem}`}/>
              </div>
              { entities.map((obligation, index) => (
                  <React.Fragment key={index}>
                      <div className={`${style.rowWarObligation} ${style.itemRow} ${index % 2 === 0 ? style.grayRow : ''}`}>
                          <div className={`${style.item}`}>
                            <input
                              className={`${styleForm.celearInput} width_full`}
                              name="startDate"
                              type="date"
                              value={obligation.startDate.toISOString().split('T')[0]}
                              onChange={(e) => handleChange(index, e)}
                            />
                          </div>
                          <div className={`${style.item}`}>
                          <input
                              className={`${styleForm.celearInput} width_full`}
                              name='endDate'
                              type="date"
                              value={obligation.endDate.toISOString().split('T')[0]}
                              onChange={(e) => handleChange(index, e)}
                            />
                          </div>
                          <div className={`${style.item}`}>
                              <div className={styleForm.radioButtonWrapper}>
                                  <label className={styleForm.squareButtonLabel}>
                                      <input
                                          className={styleForm.squareButton}
                                          type="radio"
                                          value="WAR_ZONE"
                                          name={`type-${index}`}
                                          checked={obligation.type === 'WAR_ZONE'}
                                          onChange={(e) => handleChange(index, e)}
                                      />
                                      {t.war_obligation_type_war_zone}
                                  </label>
                                  <label className={styleForm.squareButtonLabel}>
                                      <input
                                          className={styleForm.squareButton}
                                          type="radio"
                                          name={`type-${index}`}
                                          value="WORK_DUTY"
                                          checked={obligation.type === 'WORK_DUTY'}
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
        {isError &&
          <div>
            <div className='spacer_hor_S'/>
            <div className='error'>{errorMessage}</div>
          </div>
        }
    </div>
  );
}
