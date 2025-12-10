Get-LocalUser | ForEach-Object {
    $sid = (New-Object System.Security.Principal.NTAccount($_.Name)).Translate([System.Security.Principal.SecurityIdentifier])
    
    $_ | Select-Object Name, @{Name="SID"; Expression={ $sid }}
}