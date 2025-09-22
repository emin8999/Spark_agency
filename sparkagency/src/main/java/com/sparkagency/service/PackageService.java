package com.sparkagency.service;

import java.util.List;

import com.sparkagency.dto.PackageDto;

public interface PackageService {

    void deletePackage(Long id);

    PackageDto updatePackage(Long id, PackageDto packageDTO);

    PackageDto createPackage(PackageDto packageDTO);

    List<PackageDto> getAllPackages();

}
