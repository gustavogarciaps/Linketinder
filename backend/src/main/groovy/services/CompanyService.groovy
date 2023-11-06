package services

import domain.Company
import repository.CompanyDAO
import utils.OperationStatus

class CompanyService {

    private final CompanyDAO companyDAO

    CompanyService(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO
    }

    List<Company> findAll() {
        List<Company> result = companyDAO.findAll()
        return result
    }

    Company findAll(Company company) {
        Company result = companyDAO.findAll(company)
        return result
    }

    Company findById(int id) {
        Company result = companyDAO.findById(id)
        return result
    }

    OperationStatus save(Company company) {
        return companyDAO.save(company);
    }

    OperationStatus updateById(Company company) {
        return companyDAO.updateById(company)
    }

    OperationStatus deleteById(int id) {
        return companyDAO.deleteById(id)
    }
}
