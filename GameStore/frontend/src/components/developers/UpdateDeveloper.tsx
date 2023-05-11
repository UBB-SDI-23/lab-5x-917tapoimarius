import { useNavigate, useParams } from "react-router-dom"
import { DeveloperForm } from "./DeveloperForm";
import { useEffect, useState } from "react";
import { Developer } from "../../models/Developer";
import { BACKEND_API_URL } from "../../constants";
import axios from "axios";

export const UpdateDeveloper = () => {
    const navigate = useNavigate();
    const {id} = useParams();

    useEffect(() => {
        fetch(`${BACKEND_API_URL}/developers/${id}`)
            .then(res => res.json())
            .then(data => {
                setDeveloper(data);
            })
    }, []);;

    const [developer, setDeveloper] = useState<Developer>(new Developer());

	const updateDeveloper = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			console.log(developer);
			await axios.put(`${BACKEND_API_URL}/developers/${id}`, developer);
			navigate("/developers/");
		} catch (error) {
			console.log(error);
		}
	};

    return(
        <DeveloperForm
            apiCallMethod={updateDeveloper}
            developer={developer}
            setDeveloper={setDeveloper}
            btnMsg="Update developer"
        />
    )
}