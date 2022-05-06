export class Development
{
   
title: number | undefined;
activity: string | undefined;
budget: number | undefined;
state: string | undefined;
activityMonth: number | undefined;
activityYear: number | undefined;
developmentId:number | undefined;
politicalLeaderId: number |undefined;
      
}

export class leaderDevelopments
{
    candidateName: string | undefined;
candidateState: string | undefined;
politicalLeaderId: number | undefined;
politicalPartyId: number | undefined

    development: Array<Development> = [];

}