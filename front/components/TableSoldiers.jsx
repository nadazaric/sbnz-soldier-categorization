import { getTranslation } from '@/locales/TranslationHelper'
import style from '../styles/Table.module.css'
import React, { useEffect, useState } from 'react'
import CancelIcon from '@mui/icons-material/Close'

export default function TableSoldiers({ soldiers, onSoldierClick }) {
    const t = getTranslation()

    if (soldiers == null) return <div></div>
    if (soldiers.length == 0) {
        return (
            <div className={style.pageNoData}>
                <CancelIcon className={style.noDataIcon} style={{ fontSize: '120px' }} />
                <p className={style.noDataDescription}>{t.no_data}</p>
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
                {soldiers != null && soldiers.map((soldier, index) => (
                    <React.Fragment key={index}>
                        <div 
                            className={`${style.rowSoldier} ${style.itemRow} ${style.activeRow} ${index % 2 === 0 ? style.grayRow : ''}`}
                            onClick={() => {if (onSoldierClick) onSoldierClick(soldier)}}>
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