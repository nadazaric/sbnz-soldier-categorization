import { getTranslation } from '@/locales/TranslationHelper'
import style from '../styles/Table.module.css'
import React, { useEffect, useState } from 'react'
import CancelIcon from '@mui/icons-material/Close'
import AddIcon from '@mui/icons-material/Add'
import { Button } from '@mui/material'

export default function TableWorkers({ workers, onAddClick }) {
    const t = getTranslation()

    if (workers == null) return <div></div>
    if (workers.length == 0) {
        return (
            <div className={style.pageNoData}>
                <CancelIcon className={style.noDataIcon} style={{ fontSize: '120px' }} />
                <p className={style.noDataDescription}>{t.no_data}</p>
                <Button 
                    className="button raisedButton"
                    onClick={() => { if(onAddClick) onAddClick() }}>
                    <AddIcon style={{ fontSize: '18px' }} />
                    <p>{t.button_add_worker}</p>
                </Button>
            </div>
        )
    } else {
        return (
            <div>
                <div className={style.rowWorker}>
                    <div className={`${style.item} ${style.headerItem}`}>{t.worker_name}</div>
                    <div className={`${style.item} ${style.headerItem}`}>{t.worker_username}</div>
                </div>
                {workers != null && workers.map((worker, index) => (
                    <React.Fragment key={index}>
                        <div className={`${style.rowWorker} ${style.itemRow} ${index % 2 === 0 ? style.grayRow : ''}`}>
                            <div className={`${style.item}`}>{worker.name}</div>
                            <div className={`${style.item}`}>{worker.username}</div>
                        </div>
                    </React.Fragment>
                ))}
            </div>
        )
    }
}