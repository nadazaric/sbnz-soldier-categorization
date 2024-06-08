import style from '../styles/Chip.module.css'

export default function Chip({ type, title, icon }) {

    function getCSSStyle() {
        if (type == CHIP_TYPE.NEUTRAL) return style.staticChipNeutral
        else if (type == CHIP_TYPE.POSITIVE) return style.staticChipPositive
        else if (type == CHIP_TYPE.ERROR) return style.staticChipError
    }

    return(
        <div className={`${style.staticChip} ${getCSSStyle()}`}>
            {title}
            {icon}
        </div>
    )
}

export const CHIP_TYPE = {
    NEUTRAL: 'neutral',
    POSITIVE: 'positive',
    ERROR: 'error'
}