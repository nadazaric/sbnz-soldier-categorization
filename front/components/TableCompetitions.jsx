import { getTranslation } from '@/locales/TranslationHelper'
import style from '../styles/Table.module.css'
import React, { useEffect, useState } from 'react'
import CancelIcon from '@mui/icons-material/Close'
import Chip, { CHIP_TYPE } from './Chip'
import DoneIcon from '@mui/icons-material/Check'
import PersonIcon from '@mui/icons-material/PersonAdd'

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
                <div className={style.rowCompetition}>
                    <div className={`${style.item} ${style.headerItem}`}>{t.competition_name}</div>
                    <div className={`${style.item} ${style.headerItem}`}>{t.competition_position_name}</div>
                    <div className={`${style.item} ${style.headerItem}`}>{t.competition_current_number}</div>
                </div>
                {competitions != null && competitions.map((competition, index) => (
                    <React.Fragment key={index}>
                        <div 
                            className={`${style.rowCompetition} ${style.itemRow} ${style.activeRow} ${index % 2 === 0 ? style.grayRow : ''}`}
                            onClick={() => {if (onCompeitionClick) onCompeitionClick(competition)}}>
                            <div className={`${style.item}`}>{competition.name}</div>
                            <div className={`${style.item}`}>{competition.positionNumber}</div>
                            <div className={`${style.item}`}>{competition.competitors && competition.competitors.length}</div>
                            <div className={`${style.item}`}>
                                <Chip 
                                    type={competition.isDone ? CHIP_TYPE.POSITIVE : CHIP_TYPE.NEUTRAL} 
                                    title={competition.isDone ? t.competition_status_finished : t.competition_status_active} 
                                />
                            </div>
                            {!competition.isDone && 
                                <div className={`${style.item} ${style.lastItem}`}>
                                    <DoneIcon className='icon' />
                                    <PersonIcon className='icon' />
                                </div>
                            }
                        </div>
                    </React.Fragment>
                ))}
            </div>
        )
    }
}