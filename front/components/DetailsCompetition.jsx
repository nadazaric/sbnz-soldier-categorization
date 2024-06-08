/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useState } from "react";
import TableCompetitors from "./TableCompetitors";
import { getTranslation } from "@/locales/TranslationHelper";

export default function DeatilsCompetition({ competition, isOpen }) {
    const t = getTranslation()
    const [competitionInfo, setCompetitionInfo] = useState()
    const CHECK_TYPE = {
        ALL: 'all',
        SELECTED: 'selected'
    }
    const [check, setCheck] = useState(CHECK_TYPE.ALL)

    useEffect(() => {
        setCheck(CHECK_TYPE.ALL)
    }, [isOpen])

    useEffect(() => {
        setCompetitionInfo(competition)
    }, [competition])

    return(
        <div>
            {competitionInfo &&
                <div>
                    <div className="fileRadioInputs">
                        <label className="radio">
                            <input 
                                type="radio" 
                                name="radio" 
                                value={CHECK_TYPE.ALL}
                                checked={check == CHECK_TYPE.ALL}
                                onChange={() => setCheck(CHECK_TYPE.ALL)}
                            />
                            <span className="name">{t.competition_all_competitors}</span>
                        </label>

                        <label className="radio">
                            <input 
                                type="radio" 
                                name="radio" 
                                value={CHECK_TYPE.SELECTED}
                                checked={check == CHECK_TYPE.SELECTED}
                                onChange={() => setCheck(CHECK_TYPE.SELECTED)}
                            />
                            <span className="name">{t.competition_selected_competitors}</span>
                        </label>
                    </div>

                    {check == CHECK_TYPE.SELECTED &&
                        <TableCompetitors competitors={competitionInfo?.selectedCompetitors.sort((a, b) => b.score - a.score)} />
                    }
                    {check == CHECK_TYPE.ALL &&
                        <TableCompetitors competitors={competitionInfo?.competitors.sort((a, b) => b.score - a.score)} />
                    }
                </div>
            }
        </div>
    )
}