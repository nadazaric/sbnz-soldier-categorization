import Link from 'next/link'
import style from '../styles/Navbar.module.css'
import { useEffect, useState } from 'react'
import DropdownMenu from './DropdownMenu'
import { useRouter } from 'next/router'

export default function Navbar() {
    const router = useRouter()
    const [selectedOption, setSelectedOption] = useState(PAGE.SOLDIERS)
    const [locale, setLocale] = useState()
    const languageOptions = [
        { value: 'sr-Latn', text: 'SRB' },
        { value: 'en', text: 'EN' }
    ]

    useEffect(() => {
        setLocale(router.locale)
    }, [router, selectedOption])

    const changeLanguage = (e) => {
        setLocale(e.value)
        document.cookie = `NEXT_LOCALE=${e.value}; max-age=31536000; path=/`
        router.replace(router.pathname, router.asPath, { locale: e.value })
    }

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
                <div className={style.devider} />
                <DropdownMenu
                    options={languageOptions}
                    selectedDefault={locale}
                    onSelect={changeLanguage}
                    button={true}
                />
            </div>
        </div>
    )
}

export const PAGE = {
    SOLDIERS: 'soldiers',
    COMPETITIONS: 'competitions'
} 