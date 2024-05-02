import { getTranslation } from '@/locales/TranslationHelper'
import style from '../styles/Table.module.css'
import DeleteIcon from '@mui/icons-material/DeleteOutline';
import React from 'react';

export default function TableSoldiers({ soldiers, onSoldierClick }) {
    const t = getTranslation()

    function onClick(e, soldier) {
        e.stopPropagation();
        if (onSoldierClick) onDelete(soldier)
    }

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
                    <div className={`${style.rowSoldier} ${style.itemRow} ${index % 2 === 0 ? style.grayRow : ''}`}>
                        <div className={`${style.item}`}>{soldier.fullName}</div>
                        <div className={`${style.item}`}>{soldier.jmbg}</div>
                        <div className={`${style.item}`}>{soldier.months}</div>
                        <div className={`${style.item}`}>{soldier.category}</div>
                        <div className={`${style.item}`}>{soldier.monthlyContribution}</div>
                    </div>
                </React.Fragment>
            ))}
        </div>
    )
}