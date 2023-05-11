import { useEffect, useState } from "react";
import { BACKEND_API_URL } from "../../constants";
import { Developer } from "../../models/Developer";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { DeveloperForm } from "./DeveloperForm";

export const AddDeveloper = () => {
	const navigate = useNavigate();

	const [developer, setDeveloper] = useState<Developer>(new Developer());

	

	const AddDeveloper = async (event: { preventDefault: () => void }) => {
		event.preventDefault();
		try {
			console.log(developer);
			await axios.post(`${BACKEND_API_URL}/developers/`, developer);
			navigate("/developers/");
		} catch (error) {
			console.log(error);
		}
	};

	return(
		<DeveloperForm
			apiCallMethod={AddDeveloper}
			developer={developer}
			setDeveloper={setDeveloper}
			btnMsg="Add developer"
		/>
	)
};