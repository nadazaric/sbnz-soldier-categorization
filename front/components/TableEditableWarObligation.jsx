/* eslint-disable react/jsx-no-undef */
import React, { useEffect, useState } from 'react'
import style from '../styles/Table.module.css'
import styleForm from '../styles/Form.module.css'

export default function TableEditableWarObligation({ obligations, onChange }) {
  const [entities, setEntities] = useState([])
  const [isError, setIsError] = useState(false)

  useEffect(() =>{
    setEntities(obligations)
  }, [obligations])

  const handleChange = (index, e) => {
    const { name, value, type, checked } = e.target
    const [fieldName, fieldIndex] = name.split('-')
    const newValue = type === 'checkbox' ? checked : (type === 'date' ? new Date(value) : value)
    const newEntities = [...entities]
    newEntities[index][fieldName] = newValue
    setEntities(newEntities)
    setIsError(!isValidDateRange(newEntities))
    if (onChange) onChange(newEntities)
  };

  const isValidDateRange = (obligations) => {
    for (let i = 0; i < obligations.length; i++) {
      const current = obligations[i]
      if (current.startDate >= current.endDate) return false
      for (let j = i + 1; j < obligations.length; j++) {
        const next = obligations[j]
        if (current.startDate < next.endDate && current.endDate > next.startDate) {
          return false
        }
      }
    }
    return true
  }
  
  return (
    <div>
        <div>
          {entities != null && entities.length != 0 &&
            <div>
              <div className={style.rowWarObligation}>
                  <div className={`${style.item} ${style.headerItem}`}>{"Od"}</div>
                  <div className={`${style.item} ${style.headerItem}`}>{"Do"}</div>
                  <div className={`${style.item} ${style.headerItem}`}>{"Tip"}</div>
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
                                      {"Ratna zona"}
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
                                      {"Radna obaveza"}
                                  </label>
                              </div>
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
            <div className='error'>Error</div>
          </div>
        }
    </div>
  );
}
