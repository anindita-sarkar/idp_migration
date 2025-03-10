import React, { useEffect, useState, useMemo, useCallback } from 'react';
import { useAuth } from 'oidc-react';

function ManageToken() {
  const auth = useAuth();
  const userManager = auth.userManager; // ✅ Correct reference

  // Extract token details
  const accessToken = auth.userData?.access_token;
  const idToken = auth.userData?.id_token;
  const refreshToken = auth.userData?.refresh_token;
  const expiresAt = auth.userData?.expires_at;

  // Calculate expiration date
  const expDate = useMemo(() => (expiresAt ? new Date(expiresAt * 1000) : null), [expiresAt]);

  // Remaining time until token expiration
  const calculateRemainingTime = useCallback(() => {
    if (expDate) {
      return Math.max(Math.floor((expDate.getTime() - Date.now()) / 1000), 0);
    }
    return 0;
  }, [expDate]);

  const [remainingTime, setRemainingTime] = useState(calculateRemainingTime);

  // Refresh token only if it's about to expire (30s threshold)
  useEffect(() => {
    const intervalId = setInterval(() => {
      const timeLeft = calculateRemainingTime();
      setRemainingTime(timeLeft);

      if (timeLeft < 30) { // Only refresh when token is close to expiration
        userManager.signinSilent()
          .then(user => console.log("Silent refresh successful", user))
          .catch(err => {
            console.error("Silent refresh failed", err);
            if (err.error === "session_inactive" || err.error === "invalid_grant") {
              console.log("User session is inactive. Redirecting to login page...");
              userManager.signinRedirect();
            }
          });
      }
    }, 5000); // Runs every 5 seconds instead of every second

    return () => clearInterval(intervalId);
  }, [calculateRemainingTime, userManager]);

  // Format tokens for display
  const formatToken = (token) => (token ? `${token.substring(0, 8)}...${token.slice(-8)}` : null);
  const formattedTokens = {
    access: formatToken(accessToken),
    id: formatToken(idToken),
    refresh: formatToken(refreshToken),
  };

  if (!auth || !auth.userData) {
    return <div>Error: Authentication data not available.</div>;
  }

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h3>Hello, {auth.userData?.profile?.name}</h3>
      <div style={{ border: '1px solid #e1e1e1', padding: '10px', borderRadius: '5px' }}>
        <h4>Access Token (shortened)</h4>
        <p>{formattedTokens.access}</p>

        <h4>ID Token (shortened)</h4>
        <p>{formattedTokens.id}</p>

        <h4>Refresh Token (shortened)</h4>
        <p>{formattedTokens.refresh}</p>

        <h4>Token Expires In ~ </h4>
        <p>{remainingTime} seconds</p>

        <h4>Expiration Date/Time</h4>
        <p>{expDate?.toLocaleString()}</p>
      </div>
    </div>
  );
}

export default ManageToken;
