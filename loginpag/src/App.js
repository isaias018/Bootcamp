import React, { useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [isRegister, setIsRegister] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const url = isRegister
        ? "http://localhost:5000/register"
        : "http://localhost:5000/login";

      const { data } = await axios.post(url, { email, password });
      alert(data.message);
      setError("");
    } catch (err) {
      setError(err.response?.data?.message || "Error en la solicitud");
    }
  };

  // 👇 Asegúrate que el return esté dentro de la función App
  return (
    <div className="login-container">
      <div className="login-box">
        <h2>{isRegister ? "Crear Cuenta" : "Iniciar Sesión"}</h2>
        <form onSubmit={handleSubmit}>
          {error && <p className="error">{error}</p>}
          <input
            type="email"
            placeholder="Correo electrónico"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="password"
            placeholder="Contraseña"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button type="submit">
            {isRegister ? "Registrarse" : "Ingresar"}
          </button>
        </form>
        <p
          onClick={() => setIsRegister(!isRegister)}
          style={{ marginTop: "10px", cursor: "pointer", color: "blue" }}
        >
          {isRegister
            ? "¿Ya tienes cuenta? Inicia sesión"
            : "¿No tienes cuenta? Regístrate"}
        </p>
      </div>
    </div>
  );
}

export default App;
