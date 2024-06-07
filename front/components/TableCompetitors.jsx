import { getTranslation } from '@/locales/TranslationHelper'
import style from '../styles/Table.module.css'
import React, { useEffect, useState } from 'react'
import CancelIcon from '@mui/icons-material/Close'
import Chip, { CHIP_TYPE } from './Chip'

export default function TableCompetitors({ competitors }) {
    const t = getTranslation()

    if (competitors == null || competitors.length == 0) {
        return(
            <div className={style.pageNoDataMini}>
                <CancelIcon className={style.noDataIcon} style={{ fontSize: '60px' }} />
                <p className={style.noDataDescription}>{t.competition_no_competitors}</p>
            </div>
            // <div style={{marginLeft: 10, marginTop: 10}}>Nema kandidata</div>
        )
    } else {
        return (
            <div>
                <div className={style.rowCompetitor}>
                    <div className={`${style.item} ${style.headerItem}`}>{t.competitor_name}</div>
                    <div className={`${style.item} ${style.headerItem}`}>{t.competitor_jmbg}</div>
                    <div className={`${style.item} ${style.headerItem}`}>{t.competitor_dead_family_member}</div>
                    <div className={`${style.item} ${style.headerItem}`}>{t.competitor_injury}</div>
                    <div className={`${style.item} ${style.headerItem}`}>{t.competitor_score}</div>
                </div>
                {competitors != null && competitors.map((competition, index) => (
                    <React.Fragment key={index}>
                        <div className={`${style.rowCompetitor} ${style.itemRow} ${index % 2 === 0 ? style.grayRow : ''}`}>
                            <div className={`${style.item}`}>{competition.fullName}</div>
                            <div className={`${style.item}`}>{competition.jmbg}</div>
                            <div className={`${style.item}`}>{competition.deadFamilyMember}</div>
                            <div className={`${style.item}`}>{competition.injuryType}</div>
                            <div className={`${style.item}`}>{competition.score}</div>
                            <div className={`${style.item}`}>{competition.status}</div>
                        </div>
                    </React.Fragment>
                ))}
            </div>
        )
    }
}