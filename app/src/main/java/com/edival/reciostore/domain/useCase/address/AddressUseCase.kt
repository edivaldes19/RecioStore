package com.edival.reciostore.domain.useCase.address

data class AddressUseCase(
    val getAddressByUserUseCase: GetAddressByUserUseCase,
    val createAddressUseCase: CreateAddressUseCase,
    val updateAddressUseCase: UpdateAddressUseCase,
    val deleteAddressUseCase: DeleteAddressUseCase
)