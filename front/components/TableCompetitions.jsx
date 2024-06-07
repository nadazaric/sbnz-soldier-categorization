import { getTranslation } from '@/locales/TranslationHelper'
import style from '../styles/Table.module.css'
import React, { useEffect, useState } from 'react'
import CancelIcon from '@mui/icons-material/Close'
import AddIcon from '@mui/icons-material/Add'
import { Button } from '@mui/material'

export default function TableCompetitions({ competitions, onCompeitionClick, onAddClick }) {
    const t = getTranslation()

    if (competitions == null) return <div></div>
    if (competitions.length == 0) {
        return (
            <div className={style.pageNoData}>
                <CancelIcon className={style.noDataIcon} style={{ fontSize: '120px' }} />
                <p className={style.noDataDescription}>{t.no_data}</p>
                <Button 
                    className="button raisedButton"
                    onClick={() => { if(onAddClick) onAddClick() }}>
                    <AddIcon style={{ fontSize: '18px' }} />
                    <p>{t.button_add_soldier}</p>
                </Button>
            </div>
        )
    } else {
        return (
            <div>
                <div className={style.rowSoldier}>
                    <div className={`${style.item} ${style.headerItem}`}>{t.soldiers_full_name}</div>
                    <div className={`${style.item} ${style.headerItem}`}>{t.soldiers_jmbg}</div>
                    <div className={`${style.item} ${style.headerItem}`}>{t.soldiers_months}</div>
                    <div className={`${style.item} ${style.headerItem}`}>{t.soldiers_category}</div>
                    <div className={`${style.item} ${style.headerItem}`}>{t.soldiers_monthly_contribution}</div>
                </div>
                {competitions != null && competitions.map((soldier, index) => (
                    <React.Fragment key={index}>
                        <div 
                            className={`${style.rowSoldier} ${style.itemRow} ${style.activeRow} ${index % 2 === 0 ? style.grayRow : ''}`}
                            onClick={() => {if (onCompeitionClick) onSoldierClick(soldier)}}>
                            <div className={`${style.item}`}>{soldier.fullName}</div>
                            <div className={`${style.item}`}>{soldier.jmbg}</div>
                            <div className={`${style.item}`}>{soldier.months}</div>
                            <div className={`${style.item}`}>{soldier.category == 'NONE' ? t.soldiers_category_none : `${soldier.category} ${t.soldiers_category_lower}`}</div>
                            <div className={`${style.item}`}>{soldier.monthlyContribution} {t.valute}</div>
                        </div>
                    </React.Fragment>
                ))}
            </div>
        )
    }
}