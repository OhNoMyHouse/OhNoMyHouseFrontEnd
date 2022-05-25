import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8080/",
  headers: {
    "Content-Type": "application/json",
  },
});

function apiInstance() {
  const instance = axios.create({
    baseURL: "http://localhost:8080/",
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

export { apiInstance };
