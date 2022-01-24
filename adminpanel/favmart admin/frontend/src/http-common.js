import axios  from "axios";
var url = "http://localhost:5001/favmart-company-project/us-central1/app/api";
const instance = axios.create({
    baseURL: url,
    headers:{
        "Content-type": "application/json",
        // "Access-Control-Allow-Origin":url,
        // "origin":url
    }
});
export default instance;