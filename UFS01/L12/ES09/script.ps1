try {
    Get-Content "file.txt" -ErrorAction Stop
}
catch {
    Add-Content "log.txt" -Value "ERROR - Il file non è stato aperto"
}