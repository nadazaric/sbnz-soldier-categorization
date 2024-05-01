/* eslint-disable react-hooks/rules-of-hooks */
import { useRouter } from "next/router";
import sr_latn from "./sr-Latn";
import en from "./en";

export function getTranslation() {
    const router = useRouter();
    const { locale } = router;

    switch (locale) {
        case 'sr-Latn': return sr_latn
        case 'en': return en
        default: sr_latn
    }
}

export function getLanguage() {
    const router = useRouter()
    const { locale } = router
    return locale
}