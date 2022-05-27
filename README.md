# Banking by Prakhash, Roy and Yasum

Admin:
-
- Username: admin
- Password: Password123 (Case-sensitive for this)
- Only functionality is to create banks and staff accounts

Features:
-
Generates a txt when data is stored
- Annually (Done by the calendar year):
  - Business charge of £50
  - Scaling interest
<table>
  <tr><td>Interest Rate</td><td colspan="3">Range</td></tr>
  <tr><td>1%   </td><td>        £0  </td><td><= Bal <</td><td> £20000</td></tr>
  <tr><td>2%   </td><td>    £20000  </td><td><= Bal <</td><td> £50000</td></tr>
  <tr><td>4%   </td><td>    £50000  </td><td><= Bal <</td><td> £200000</td></tr>
  <tr><td>5%   </td><td>   £200000  </td><td><= Bal</td><td></td></tr>
</table>

Account types:
-
  - Business Account
    - Business charge
  - ISA Account
    - Government bonus of 25%
    - Taking money out will result in a 25% withdrawl charge (follows rules from https://www.gov.uk/lifetime-isa)
    - Scaling interest
  - Current Account
    - Absolutely nothing special
