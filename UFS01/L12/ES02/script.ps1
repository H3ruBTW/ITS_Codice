$src = "Inp"
$files = Get-ChildItem $src -File -Recurse 

$files | Group-Object Extension | ForEach-Object {
    $ext = $_.Name.trim(".")
    $count = $_.count
    Write-Output "${ext}: $count"
}