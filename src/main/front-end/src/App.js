import React, { useState, useEffect } from "react";
import "./App.css";
import axios from "axios";

// const fakeUserAPI = "http://localhost:8080/fakeUsers";
const fakeUserAPI =
  "https://connect-backend-to-frontend.azurewebsites.net/fakeUsers";

const FakeUsers = () => {
  const [users, setUsers] = useState([]);
  const [pronouns, setPronouns] = useState("");

  const getFakeUsers = () => {
    axios.get(fakeUserAPI).then((res) => {
      setUsers(res.data);
    });
  };

  useEffect(() => {
    getFakeUsers();
  }, [pronouns]);

  const handleChange = (e) => {
    setPronouns(e.target.value);
  };

  const handleSubmit = (userId) => (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("pronouns", pronouns);
    // console.log(pronouns);
    console.log(userId);
    axios.post(`${fakeUserAPI}/${userId}/update`, formData);
    setPronouns("");
  };

  return users.map((user, index) => {
    return (
      <div className="user" key={index}>
        <h3>{user.firstName}</h3>
        <p>{user.nationality}</p>
        <p>{user.pronouns}</p>
        <form onSubmit={handleSubmit(user.id)}>
          <input
            name="pronouns"
            value={pronouns}
            onChange={handleChange}
            type="text"
            placeholder="pronouns"
          />
          <button type="submit">Add Pronouns</button>
        </form>
      </div>
    );
  });
};

function App() {
  return (
    <>
      <FakeUsers />
    </>
  );
}

export default App;
