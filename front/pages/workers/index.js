import AddWorker from "@/components/AddWorker";
import { DialogWithHeader } from "@/components/Dialog";
import { ButtonHeader } from "@/components/Header";
import TableWorkers from "@/components/TableWorkers";
import { BACK_BASE_URL } from "@/helper/environment";
import { getTranslation } from "@/locales/TranslationHelper";
import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";

export default function Workers() {
    const t = getTranslation()
    const [workers, setWorkers] = useState([])
    const [openDialog, setOpenDialog] = useState(false)

    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/user/workers`)
            .then(response => { setWorkers(response.data) })
            .catch(_error => {})
    }, [])

    const saveWorker = async (formData) => {
        try {
            const response = await axios.post(`${BACK_BASE_URL}/user/register`, formData, {
                headers: { 'Content-Type': 'application/json' }
            })
            if (response.status === 201) {
                setWorkers(prevWorkers => [...prevWorkers, response.data])
                setOpenDialog(false)
            }
            else { }
        } catch (error) { }
    }

    return(
        <div className="page">
            <ButtonHeader 
                title={t.workers_header_title}
                onAddClick={() => setOpenDialog(true)} 
            />
            <TableWorkers
                workers={workers}
                onAddClick={() => setOpenDialog(true)}
            />
            <DialogWithHeader
                isOpen={openDialog}
                width={400}
                onCloseModal={() => setOpenDialog(false)}
                title={t.worker_add_title}
            >
                <AddWorker
                    isOpen={openDialog}
                    onSave={saveWorker}
                />
            </DialogWithHeader>
        </div>
    )
}