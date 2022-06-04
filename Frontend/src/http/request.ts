import axios from "axios";
// import { errorCode } from "./error-code";

export default axios.create({
    baseURL: import.meta.env.VITE_APP_REQUEST_URL,
    timeout: 15000,
    headers: { 'Content-Type': 'application/json;charset=utf-8' },
})