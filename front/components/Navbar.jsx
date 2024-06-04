import Link from 'next/link'
import style from '../styles/Navbar.module.css'
import { useEffect, useState } from 'react'
import { DropdownMenuNavbarOption } from './DropdownMenu'
import { useRouter } from 'next/router'
import { getTranslation } from '@/locales/TranslationHelper'
import { getUserRole, logOut } from '@/helper/helper'

export default function Navbar() {
    const t = getTranslation()
    const router = useRouter()
    const [selectedOption, setSelectedOption] = useState(PAGE.SOLDIERS)
    const [locale, setLocale] = useState()
    const [role, setRole] = useState(null)
    const languageOptions = [
        { value: 'sr-Latn', text: 'SRB' },
        { value: 'en', text: 'EN' }
    ]

    useEffect(() => {
        setLocale(router.locale)
    }, [router, selectedOption])

    useEffect(() => {
        setRole(getUserRole())
    }, [router])

    const changeLanguage = (e) => {
        setLocale(e.value)
        router.replace(router.pathname, router.asPath, { locale: e.value })
    }

    return(
        <div className={style.wrapper}>
            {
                console.log('NAVVVVVVVVVVV ' + role)
            }
            <div className={style.logo}>
                <Link
                    className={`${style.logo}`}
                    href={`/${PAGE.SOLDIERS}`}
                    onClick={() => setSelectedOption(PAGE.SOLDIERS)}
                >
                    {t.logo}
                </Link>
            </div>

            <div className={style.options}>
                {role == 'WORKER' &&
                    <Link
                        className={`${style.option} ${selectedOption === PAGE.SOLDIERS ? style.selectedOption : ''}`}
                        href={`/${PAGE.SOLDIERS}`}
                        onClick={() => setSelectedOption(PAGE.SOLDIERS)}
                    >
                        {t.navbar_option_soldiers}
                    </Link>
                }

                {role == 'WORKER' &&
                    <Link
                        className={`${style.option} ${selectedOption === PAGE.COMPETITIONS ? style.selectedOption : ''}`}
                        href={`/${PAGE.COMPETITIONS}`}
                        onClick={() => setSelectedOption(PAGE.COMPETITIONS)}
                    >
                        {t.navbar_option_competitions}
                    </Link>
                }

                {role == 'ROLE_ADMIN' &&
                    <Link
                        className={`${style.option} ${selectedOption === PAGE.WORKERS ? style.selectedOption : ''}`}
                        href={`/${PAGE.WORKERS}`}
                        onClick={() => setSelectedOption(PAGE.WORKERS)}
                    >
                        {"radnici"}
                    </Link>
                }

                {role != null &&
                    <Link
                        className={`${style.option}`}
                        href="/login"
                        onClick={() => {
                            setSelectedOption('')
                            logOut()
                        }}
                    >
                        {'izloguj se'}
                    </Link>
                }

                {role != null &&
                    <div className={style.devider} />
                }

                <DropdownMenuNavbarOption
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
    COMPETITIONS: 'competitions',
    WORKERS: 'workers',
    LOGOUT: 'logout'
} 