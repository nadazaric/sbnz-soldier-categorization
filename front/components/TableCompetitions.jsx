import { getTranslation } from '@/locales/TranslationHelper'
import style from '../styles/Table.module.css'
import React, { useEffect, useState } from 'react'
import CancelIcon from '@mui/icons-material/Close'

export default function TableCompetitions({ competitions, onCompeitionClick }) {
    const t = getTranslation()

    if (competitions == null) return <div></div>
    if (competitions.length == 0) {
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
                </div>
                {competitions != null && competitions.map((competition, index) => (
                    <React.Fragment key={index}>
                        <div 
                            className={`${style.rowSoldier} ${style.itemRow} ${style.activeRow} ${index % 2 === 0 ? style.grayRow : ''}`}
                            onClick={() => {if (onCompeitionClick) onSoldierClick(competition)}}>
                            <div className={`${style.item}`}>{competition.name}</div>
                            <div className={`${style.item}`}>{competition.positionNumber}</div>
                            <div className={`${style.item}`}>{competition.isDone}</div>
                        </div>
                    </React.Fragment>
                ))}
            </div>
        )
    }
}