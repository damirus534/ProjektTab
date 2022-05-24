export class AdminRaportReqbody {
  constructor(public categoryId : number | null, public beginning : string | null, public ending :string | null, public raportType : number | null) {
  }
}
