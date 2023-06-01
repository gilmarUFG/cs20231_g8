import axios from "axios";

const apiUrl = process.env.API_URL;

export const uploadFile = (file: File) => {
    const formData: FormData = new FormData();
    formData.append('file', file);
    axios.post(`${apiUrl}/files`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
    .then(response => response.data)
    .catch(error => console.log(error));
}