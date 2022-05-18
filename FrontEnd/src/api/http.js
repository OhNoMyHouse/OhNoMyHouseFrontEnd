import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8080/myapp_rest",
  headers: {
    "Content-Type": "application/json",
  },
});
