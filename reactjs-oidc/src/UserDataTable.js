import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Paper,
  TableContainer
} from '@mui/material'
import CheckIcon from '@mui/icons-material/Check'

function UserDataTable ({ userData }) {
 
  return (
    <div style={{ maxWidth: "50%" }}>
      <TableContainer component={Paper}>
        <Table size="small" aria-label="a dense table">
          <TableHead>
            <TableRow>
              <TableCell>Username</TableCell>
              <TableCell>Name</TableCell>
              <TableCell>Grants</TableCell>
              <TableCell>Added</TableCell>
              <TableCell>Last Access</TableCell>
              <TableCell>Create Collection</TableCell>
              <TableCell>Administrator</TableCell>
              <TableCell>UserID</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            <TableRow>
              <TableCell>{userData.username || "N/A"}</TableCell>
              <TableCell>{userData.displayName || "N/A"}</TableCell>
              <TableCell>{userData.statistics?.collectionGrantCount ?? 0}</TableCell>
              <TableCell>
                {userData.statistics?.created
                  ? new Date(userData.statistics.created).toLocaleString()
                  : "N/A"}
              </TableCell>
              <TableCell>
                {userData.statistics?.lastAccess
                  ? new Date(userData.statistics.lastAccess * 1000).toLocaleString()
                  : "N/A"}
              </TableCell>
              <TableCell>
                {userData.privileges?.canCreateCollection ? <CheckIcon /> : null}
              </TableCell>
              <TableCell>
                {userData.privileges?.canAdmin ? <CheckIcon /> : null}
              </TableCell>
              <TableCell>{userData.userId || "N/A"}</TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  )
}

export default UserDataTable
