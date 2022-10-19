<?php

namespace App\Repository;

use App\Entity\Carteb;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Carteb|null find($id, $lockMode = null, $lockVersion = null)
 * @method Carteb|null findOneBy(array $criteria, array $orderBy = null)
 * @method Carteb[]    findAll()
 * @method Carteb[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CartebRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Carteb::class);
    }

    // /**
    //  * @return Carteb[] Returns an array of Carteb objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Carteb
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
