import Link from 'next/link'
import style from '../styles/Navbar.module.css'
import { useState } from 'react'

export default function Navbar() {
    const [selectedOption, setSelectedOption] = useState(PAGE.SOLDIERS)

    return(
        <div className={style.wrapper}>
            <div className={style.logo}>
                <Link
                    className={`${style.logo}`}
                    href={`/${PAGE.SOLDIERS}`}
                    onClick={() => setSelectedOption(PAGE.SOLDIERS)}
                >
                    Dutyify
                </Link>
                
            </div>
            <div className={style.options}>
                <Link
                    className={`${style.option} ${selectedOption === PAGE.SOLDIERS ? style.selectedOption : ''}`}
                    href={`/${PAGE.SOLDIERS}`}
                    onClick={() => setSelectedOption(PAGE.SOLDIERS)}
                >
                    Vojnici
                </Link>
                <Link
                    className={`${style.option} ${selectedOption === PAGE.COMPETITIONS ? style.selectedOption : ''}`}
                    href={`/${PAGE.COMPETITIONS}`}
                    onClick={() => setSelectedOption(PAGE.COMPETITIONS)}
                >
                    Konkursi
                </Link>
            </div>
        </div>
    )
}

export const PAGE = {
    SOLDIERS: 'soldiers',
    COMPETITIONS: 'competitions'
} 