package ru.az.sfr.services.ldap.dto;

import lombok.Data;
import ru.az.sfr.services.ldap.model.UserAD;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
public class EmployeeDtoV1 {

    private String lastName;
    private String firstName;
    private String middleName;
    private String email;
    private String phone;
    private String kspd;
    private String accountName;
    private String principalName;
    private String description;
    private String wsName;
    private String depName;
    private String posName;
    private String popName;

    public static Optional<EmployeeDtoV1> createFromUserAD(UserAD userAD) {
        if (userAD == null) return Optional.empty();
        EmployeeDtoV1 employee = new EmployeeDtoV1();
        Map<FIO, String> fio = getFio(userAD.getDisplayName());
        employee.setLastName(fio.get(FIO.LAST_NAME));
        employee.setFirstName(fio.get(FIO.FIRST_NAME));
        employee.setMiddleName(fio.get(FIO.MIDDLE_NAME));
        employee.setEmail(userAD.getMail());
        employee.setPhone(userAD.getMobile());
        employee.setKspd(userAD.getTelephoneNumber());
        employee.setAccountName(userAD.getSAMAccountName());
        employee.setPrincipalName(userAD.getUserPrincipalName());
        employee.setWsName(getWSName(userAD.getInfo()));
        employee.setDepName(userAD.getDepartment());
        employee.setPosName(userAD.getDescription());
        employee.setPopName(userAD.getL());
        employee.setDescription(
                ((userAD.getL() == null ? "" : userAD.getL()) + " " +
                        (userAD.getStreetAddress() == null ? "" : userAD.getStreetAddress())).trim()
        );
        return Optional.of(employee);
    }

    private static Map<FIO, String> getFio(String s) {
        Map<FIO, String> fio = new HashMap<>();
        if (s == null) return fio;
        String[] split = s.trim().split("\\s+");
        switch (split.length) {
            case 0:
                fio.put(FIO.LAST_NAME, "");
                fio.put(FIO.FIRST_NAME, "");
                fio.put(FIO.MIDDLE_NAME, "");
                break;
            case 1:
                fio.put(FIO.LAST_NAME, split[0]);
                fio.put(FIO.FIRST_NAME, "");
                fio.put(FIO.MIDDLE_NAME, "");
                break;
            case 2:
                fio.put(FIO.LAST_NAME, split[0]);
                fio.put(FIO.FIRST_NAME, split[1]);
                fio.put(FIO.MIDDLE_NAME, "");
                break;
            default:
                fio.put(FIO.LAST_NAME, split[0]);
                fio.put(FIO.FIRST_NAME, split[1]);
                fio.put(FIO.MIDDLE_NAME, split[2]);
        }
        return fio;
    }

    enum FIO {
        LAST_NAME,
        FIRST_NAME,
        MIDDLE_NAME;
    }

    private static String getWSName(String wsName) {
        if (wsName == null) return null;
        String[] split = wsName.replaceAll("\\s+", "").split(":");
        return split.length == 2 ? split[1] : null;
    }
}
